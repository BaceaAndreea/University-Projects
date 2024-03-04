bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

extern exit,printf ,fopen,fclose,fread               ;
import exit msvcrt.dll 
import printf msvcrt.dll 
import fopen msvcrt.dll 
import fclose msvcrt.dll 
import fread msvcrt.dll 
segment data use32 class=data
    ; ...
    file_name db "exercitiu_1.txt",0
    acces_mode db "r",0
    file_descriptor dd -1
    len equ 100
    nr_carac_citite dd 0
    text times (len+1) db 0
    format db "litera mare %c a aparut de %d ",0
    aparitii times 26 db 0 ;vector de aparitii 
    maxim dd 0
    numar dd 0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword acces_mode
        push dword file_name
        call [fopen]
        add esp,4*2
        
        mov [file_descriptor],eax
        cmp eax,0
        je final
        
        push dword [file_descriptor]
        push dword len 
        push dword 1
        push dword text
        call [fread]
        add esp,4*4
        
        mov [nr_carac_citite],eax
        mov bl,65
        mov edi,0;fur positionen 
        while:
            cmp bl,91
            je endwhile
            mov esi,text
            mov ecx,[nr_carac_citite] 
            mov edx,0
            jecxz endfor
                for:
                    lodsb
                    cmp al,bl
                    je if 
                    jmp endif 
                    if:
                    inc dl
                    endif: 
                    loop for
                    mov byte[aparitii+edi],dl
                endfor:
            inc bl
            inc edi
        jmp while
        endwhile
        
        mov esi,aparitii
        mov ebx,65
        while_number:
            cmp ebx,91
            jge endwhilenumber
            lodsb
            cmp al,byte[maxim]
            jle endifmaxim
            mov byte[maxim],al
            mov dword[numar],ebx
            endifmaxim:
            inc ebx
        jmp while_number
        endwhilenumber:
            
        mov eax,0
        mov al,byte[maxim]
        push dword eax
        push dword [numar]
        push dword format
        call [printf]
        add esp,4*4
        
        push dword [file_descriptor]
        call [fclose]
        add esp,4
        final:
        
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
